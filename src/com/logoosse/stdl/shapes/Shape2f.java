/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.logoosse.stdl.shapes;

import java.util.LinkedList;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Logoosse
 * @version 1.0
 * Shape 2d to detecte datas in spatial tree
 */
public class Shape2f {
    private final LinkedList<Point2f> vertices;
    
    /**
     * 
     * @param a list of vertices of the shape.
     * Constructor for 2D Shape with a list of vertices. use clone for secure.
     */
    public Shape2f(LinkedList<Point2f> a){
        this.vertices=new LinkedList();
        a.stream().forEach((v) -> {
            this.vertices.add((Point2f)v.clone());
        });
    }
    
    /**
     * 
     * @param sh Shape2f
     *  Create shape with another shape, use clone for secure references.
     */
    public Shape2f(Shape2f sh){
        this.vertices=new LinkedList();
        sh.vertices.stream().forEach((v) -> {
            this.vertices.add((Point2f)v.clone());
        });
    }
    
    /**
     * 
     * @return a clone of the shape. 
     */
    @Override
    public Object clone(){
        Shape2f sh = new Shape2f(this.vertices);
        return sh;
    }
    

    /**
     * 
     * @return a clonned list of vertices.
     */
    public LinkedList<Point2f> getVertices(){
        LinkedList<Point2f> bnd = new LinkedList();
        this.vertices.stream().forEach((v) -> {
            bnd.add((Point2f)v.clone());
        });
        
        return bnd;
    }
    /**
     * 
     * @param p point to test
     * @return if is in or not
     * Use Lascha Lagidse algorithm to dertemine if point is in
     */
    public boolean contain(Point2f p){
        boolean isIn=false;
        Point2f lastPoint= this.vertices.getLast();
        for (Point2f v : this.vertices){
             if((v.getY() < p.getY() && lastPoint.getY()>=p.getY() || lastPoint.getY() < p.getY() && v.getY()>=p.getY()) && (v.getX() <= p.getX() || lastPoint.getX() <= p.getX())){
                 if(v.getX() + (p.getY() - lastPoint.getY())/(lastPoint.getY()-v.getY())*(lastPoint.getX() - v.getX())<p.getX()){
                     isIn=!isIn;
                 }
             }
        lastPoint=v;
        }
        return isIn;
    }
    
    public boolean intersect(Point2f p){
        boolean isIn=false;
        Point2f lastPoint= this.vertices.getLast();
        for (Point2f v : this.vertices){
            Vector2f distAB = new Vector2f(lastPoint.getX()-v.getX(), lastPoint.getY()-v.getY());
            Vector2f distAC = new Vector2f(lastPoint.getX()-p.getX() , lastPoint.getY()-p.getY());
            Vector2f cp = new Vector2f(distAB.getX()* distAC.getY(), distAB.getY()*distAC.getX());
            if(cp.equals(new Vector2f(0,0))){
                float KAC=distAB.dot(distAC);
                float KAB =distAB.dot(distAB);
                System.out.println(lastPoint +"-" + v);
                System.out.println(distAB +"-" + distAC);
                System.out.println(KAC +"-" + KAB);
                if (KAC==0 || KAC==KAB || (0<KAC && KAC<KAB)){
                    isIn=true;
                    break;
                }
            }
         lastPoint= v;    
        }
        return isIn;
    }
    
      /**
     * @param sh shape to compare
     * @return if the shape containe the other shape
     */
    public boolean contain (Shape2f sh){
        boolean isIn=true;
        for(Point2f v : sh.getVertices()){
            if (!this.contain(v)){
                isIn=false;
                break;
            }
        }
        return isIn;
    }
}

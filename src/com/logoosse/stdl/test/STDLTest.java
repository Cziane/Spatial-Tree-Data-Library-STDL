/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logoosse.stdl.test;

import com.logoosse.stdl.shapes.Shape2f;
import java.util.LinkedList;
import javax.vecmath.Point2f;

/**
 *
 * @author Logoosse
 */
public  class STDLTest {
    
    public static void main(String[] args) {
        Point2f p = new Point2f(10, 0);
        LinkedList<Point2f> vertices= new LinkedList();
        vertices.add(new Point2f(0, 0));
        vertices.add(new Point2f(10, 0));
        vertices.add(new Point2f(10, 10));
        vertices.add(new Point2f(0, 10));
        Shape2f sh = new Shape2f(vertices);
        
        //test with another shapes
        System.out.println("forme contain the point p : " + sh.intersect(p));
        LinkedList<Point2f> vertices2= new LinkedList();
        vertices2.add(new Point2f(1, 1));
        vertices2.add(new Point2f(1, 2));
        vertices2.add(new Point2f(2, 2));
        vertices2.add(new Point2f(2, 1));
        
        Shape2f sh2 = new Shape2f(vertices2);
        
        System.out.println("forme contain the forme sh2 : " + sh.contain(sh2));
    }
}

package main;
import java.util.Scanner;

import connector.DBConnector;
import connector.MYsqlConnector;
import viewer.StudentViewer;
public class StudentMain {
   public static void main(String[] args) {
    
    DBConnector connector  = new MYsqlConnector();
    Scanner scanner = new Scanner(System.in);
    
    StudentViewer viewer = new StudentViewer(scanner, connector);
    
    viewer.showMenu();
}
}

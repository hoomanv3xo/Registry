import java.util.*;
public class Build { public static void main(String[] a){
  for (int N : new int[]{1000,10000,100000}) {
    Vector<Student> sv=new Vector<>(); Vector<Mark> mv=new Vector<>(); Vector<Course> cv=new Vector<>(); Random r=new Random(1);
    for(int i=0;i<N;i++) sv.add(new Student(""+(1000000+i),"f","l","a","c","p","pc","ph","e"));
    for(int i=0;i<200;i++) cv.add(new Course("C"+i,"n","c"));
    for(int i=0;i<N;i++) for(int j=0;j<5;j++) mv.add(new Mark(""+(1000000+i),"C"+r.nextInt(200),50));
    long t=System.nanoTime(); StudList s=new StudList(sv); CourList c=new CourList(cv); MarkList m=new MarkList(mv); m.makeSubList("x",true);
    System.out.println(N+" students: index build "+(System.nanoTime()-t)/1e6+" ms (one time, at startup)"); } } }

import java.awt.geom.Point2D;
import java.util.*;

public class RotatingCaliper {

    static int orientation(Point2D.Double p, Point2D.Double q, Point2D.Double r){
        double x = area(p, q, r);

        if (x > 0) {
            return 1;
        }
        if (x < 0) {
            return -1;
        }
        return 0;
    }


    static double area(Point2D.Double p, Point2D.Double q, Point2D.Double r){
        return ((p.y - q.y) * (q.x - r.x)
                - (q.y - r.y) * (p.x - q.x));
    }


    static double absArea(Point2D.Double p, Point2D.Double q, Point2D.Double r){
        return Math.abs(area(p, q, r));
    }


    static double dist(Point2D.Double p1, Point2D.Double p2){
        return ((p1.x - p2.x) * (p1.x - p2.x)
                + (p1.y - p2.y) * (p1.y - p2.y));
    }


    static ArrayList<Point2D.Double> convexHull(Point2D.Double[] points) {
        int n = points.length;

        Point2D.Double min = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);

        int ind = -1;

        for (int i = 0; i < n; i++) {
            if (min.y > points[i].y) {
                min.y = points[i].y;
                min.x = points[i].x;
                ind = i;
            }
            else if (min.y == points[i].y
                    && min.x > points[i].x) {
                min.x = points[i].x;
                ind = i;
            }
        }
        points[ind] = points[0];
        points[0] = min;


        Arrays.sort(points, 1, n,
                (o1, o2) -> {
                    int o = orientation(min, o1, o2);

                    if (o == 0.0) {
                        return Double.compare(dist(o1, min), dist(o2, min));
                    }

                    if (o == 1.0) {
                        return 1;
                    }
                    return -1;
                });

        Stack<Point2D.Double> st = new Stack<>();
        st.push(points[0]);

        int k;
        for (k = 1; k < n - 1; k++) {
            if (orientation(points[0],
                    points[k],
                    points[k + 1])
                    != 0)
                break;
        }

        st.push(points[k]);

        for (int i = k + 1; i < n; i++) {
            Point2D.Double top = st.pop();

            while (orientation(st.peek(),
                    top,
                    points[i])
                    >= 0) {
                top = st.pop();
            }

            st.push(top);
            st.push(points[i]);
        }

        return new ArrayList<>(st);
    }

    static String[] getUsernames(Point2D.Double first, Point2D.Double second, Point2D.Double[] points, String[] usernames){


        String[] users = new String[2];

        for(int i = 0; i< points.length; i++){

            if(points[i].x == first.x && points[i].y == first.y){
                if(users[1] == null){
                    users[1] = usernames[i];
                }else{
                    users[1] = users[1] + ", " + usernames[i];
                }
            }
            if(points[i].x == second.x && points[i].y == second.y){
                if(users[0] == null){
                    users[0] = usernames[i];
                }else{
                    users[0] = users[0] + ", " + usernames[i];
                }
            }
        }
        return users;
    }

    static String rotatingCaliper(Point2D.Double[] points, String[] usernames){

        Point2D.Double[] points2 = points.clone();

        ArrayList<Point2D.Double> convexHull = convexHull(points);

        int n = convexHull.size();

        Point2D.Double[] hull = new Point2D.Double[n];

        n = 0;


        while (n < convexHull.size()) {
            hull[n] = convexHull.get(n++);
        }

        if (n == 2 && hull[0].equals(hull[1])) {
            return "Users live in the same spot";
        }
        else if (n == 2){
            String [] users = getUsernames(hull[0],hull[1],points2,usernames);
            double distance = Math.sqrt(dist(hull[0], hull[1]));
            return String.format("The furthest distance between users: %s. The distance separates the users: %s and %s", distance, users[0], users[1]);
        }

        int k = 1;

        while (absArea(hull[n - 1],
                hull[0],
                hull[(k + 1) % n])
                > absArea(hull[n - 1],
                hull[0],
                hull[k])) {
            k++;
        }

        double res = 0;
        Point2D.Double first = null;
        Point2D.Double second = null;

        for (int i = 0, j = k; i <= k && j < n; i++) {
            res = Math.max(res, Math.sqrt(dist(hull[i], hull[j])));

            while (j < n
                    && absArea(hull[i],
                    hull[(i + 1) % n],
                    hull[(j + 1) % n])
                    > absArea(hull[i],
                    hull[(i + 1) % n],
                    hull[j])) {

                double a = Math.sqrt(dist(hull[i], hull[(j + 1) % n]));
                if(a >= res){
                    res = a;
                    first = hull[i];
                    second = hull[(j + 1) % n];
                }
                res = Math.max(res, Math.sqrt(dist(hull[i], hull[(j + 1) % n])));
                j++;
            }
        }

        String[] users = getUsernames(first,second,points2,usernames);

        return String.format("The furthest distance between users: %s. The distance separates the users: %s and %s", res, users[0], users[1]);
    }

}
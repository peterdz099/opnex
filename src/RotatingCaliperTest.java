import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.geom.Point2D;


class RotatingCaliperTest {

    @Test
    void testRotatingCaliper1() {
        Point2D.Double[] points = { new Point2D.Double(-37.3159, 81.1496),
                new Point2D.Double(-37.3159, 81.1496),
                new Point2D.Double(40.3467, -30.131),
                new Point2D.Double(40.3467, -30.131)};

        String[] usernames = new String[]{"user1", "user2", "user3", "user4"};

        String result = RotatingCaliper.rotatingCaliper(points, usernames);
        String expected = "The furthest distance between users: 135.70133151564875. The distance separates the users: user1, user2 and user3, user4";
        Assertions.assertEquals(expected, result);
    }
    @Test
    void testRotatingCaliper2() {
        Point2D.Double[] points = { new Point2D.Double(-37.3159, 81.1496),
                new Point2D.Double(-37.3159, 81.1496),
                new Point2D.Double(-37.3159, 81.1496),
                new Point2D.Double(-37.3159, 81.1496)};

        String[] usernames = new String[]{"user1", "user2", "user3", "user4"};

        String result = RotatingCaliper.rotatingCaliper(points, usernames);
        String expected = "Users live in the same spot";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testRotatingCaliper3() {
        Point2D.Double[] points = { new Point2D.Double(-37.3159, 81.1496),
                new Point2D.Double(40.3467, -40.131),
                new Point2D.Double(40.3467, -40.131),
                new Point2D.Double(40.3467, -40.131),
                new Point2D.Double(40.3467, -30.131) };

        String[] usernames = new String[]{"user1", "user2", "user3", "user4","user5"};

        String result = RotatingCaliper.rotatingCaliper(points, usernames);
        String expected = "The furthest distance between users: 144.01549699639966. The distance separates the users: user1 and user2, user3, user4";
        Assertions.assertEquals(expected, result);
    }
    @Test
    void testRotatingCaliper4() {
        Point2D.Double[] points = { new Point2D.Double(-37.3159, 81.1496),
                new Point2D.Double(-34.3159, 81.1496),
                new Point2D.Double(40.3467, -30.131),
                new Point2D.Double(50.3467, -20.131),
                new Point2D.Double(40.3467, -40.131),
                new Point2D.Double(20.1677, -10.6789),
                new Point2D.Double(10.3456, 20.6419),
                new Point2D.Double(50.3456, 10.6419),
                new Point2D.Double(40.12456, 20.5419),
                new Point2D.Double(30.24788, -20.545419) };

        String[] usernames = new String[]{"johnd", "mor_2314", "kevinryan", "donero", "derek", "david_r", "snyder", "hopkins", "kate_h", "jimmie_k"};

        String result = RotatingCaliper.rotatingCaliper(points, usernames);
        String expected = "The furthest distance between users: 144.01549699639966. The distance separates the users: johnd and derek";
        Assertions.assertEquals(expected, result);
    }
}
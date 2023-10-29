package model;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CommentTest {
    private Comment comment;

    @Before
    public void setUp() {
        comment = new Comment(1, "user@example.com", "John", 123, "This is a test comment");
    }

    @Test
    public void testGetCurrentDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date commentDate = dateFormat.parse(comment.getCurrentDate());

        Date currentDate = new Date();

        long timeDifference = Math.abs(commentDate.getTime() - currentDate.getTime());
        long maxAllowedDifference = 1000; // 1 second
        assertEquals(0, timeDifference, maxAllowedDifference);
    }

    @Test
    public void testAddUserVote() {
        assertEquals(0, comment.getLike());
        assertEquals(0, comment.getDislike());

        comment.addUserVote("Alice", "like");
        comment.addUserVote("Bob", "dislike");
        comment.addUserVote("Charlie", "like");

        assertEquals(2, comment.getLike());
        assertEquals(1, comment.getDislike());
    }

    @Test
    public void testAddUserVoteInvalid() {
        comment.addUserVote("Eve", "invalid");

        assertEquals(0, comment.getLike());
        assertEquals(0, comment.getDislike());
    }

}
z
package connect_hub.ContentCreation;

public class ContentFactory {

    public static Content createContent(String type, String contentId, String authorId, String caption, String photo) {
        if ("post".equalsIgnoreCase(type)) {
            return new Post(contentId, authorId, caption, photo);
        } else if ("story".equalsIgnoreCase(type)) {
            return new Story(contentId, authorId, caption, photo);
        }
        throw new IllegalArgumentException("Invalid content type");
    }
}


package jac.project.restaurants.restaurants_be.imgs;

public class ImgsDTO {
    private String path;
    public ImgsDTO(){}
    public ImgsDTO(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

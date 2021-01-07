package domain;

public class ProductV2 extends ProductV1 {
    private String details;
    private String details2;

    public ProductV2(){
        super();
    }

    public ProductV2(String id, String name, String details,  String details2){
        super(id, name);
        this.details = details;
        this.details2 = details2;
    }

}

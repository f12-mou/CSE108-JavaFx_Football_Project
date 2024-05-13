package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToClientPriceList implements Serializable {
    private List<Double>priceList;
    public List<Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Double> priceList) {
        this.priceList = priceList;
    }



    public ToClientPriceList() {
       priceList=new ArrayList<>();
    }

}

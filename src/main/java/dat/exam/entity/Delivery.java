package dat.exam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate deliveryDate;
    private String fromWareHouse;
    private String destination;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delivery")
    private List<ProductOrder> productOrderList;

    @ManyToOne
    private Van van;

    public Delivery(LocalDate deliveryDate, String fromWareHouse, String destination) {
        this.deliveryDate = deliveryDate;
        this.fromWareHouse = fromWareHouse;
        this.destination = destination;
    }

    public double getDeliveryWeight() {
        double deliveryWeight = 0;
        for (int i = 0; i < productOrderList.size(); i++) {
            deliveryWeight += productOrderList.get(i).getWeight();
        }
        return deliveryWeight;
    }
}

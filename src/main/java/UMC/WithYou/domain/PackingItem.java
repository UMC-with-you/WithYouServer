package UMC.WithYou.domain;


import UMC.WithYou.domain.dummy.DummyMember;
import UMC.WithYou.domain.dummy.DummyTravel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "packing_item")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PackingItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private DummyMember creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packer_id")
    private DummyMember packer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private DummyTravel travel;

    private Boolean isChecked;
    private String itemName;

    public static PackingItem createPackingItem(DummyTravel travelId, String itemName){
        return PackingItem.builder()
                .travel(travelId)
                .itemName(itemName)
                .isChecked(false)
                .build();
    }

    public Boolean toggleCheckbox(){
        isChecked = !isChecked;
        return isChecked;
    }

    public boolean isPackerChosen(){
        return packer == null;
    }

    public void setPacker(DummyMember packer){
        this.packer = packer;
    }

}

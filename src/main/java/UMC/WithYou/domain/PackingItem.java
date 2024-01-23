package UMC.WithYou.domain;


import UMC.WithYou.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "creator_id")
//    private Member creator;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "packer_id")
//    private Member packer;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "travel_id")
//    private Travel travel;

    private boolean isChecked;
    private String itemName;
}
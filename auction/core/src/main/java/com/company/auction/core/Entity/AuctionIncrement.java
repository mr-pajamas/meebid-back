package com.company.auction.core.Entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

/**
 * Created by admin on 2017/5/14.
 */
@Entity
@Table(name = "auction_increment")
public class AuctionIncrement {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    private String id;

    /**
     * 拍卖会ID
     */
    @Column(name = "auctionId")
    private String auctionId;

}

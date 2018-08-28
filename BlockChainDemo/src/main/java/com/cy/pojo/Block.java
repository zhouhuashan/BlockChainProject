package com.cy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    private Integer bIndex;

    private String bHash;

    private long bTimestamp;

    private List<Transaction> bTransaction;

    private Integer bNonce;

    private String bPreviousHash;

}
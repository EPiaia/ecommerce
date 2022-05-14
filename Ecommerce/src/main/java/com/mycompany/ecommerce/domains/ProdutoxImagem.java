package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "PRODUTOXIMAGEM")
public class ProdutoxImagem implements Serializable {

    @EmbeddedId
    private ProdutoxImagemPK produtoxImagemPK;
    @NotNull
    @Column(name = "PXI_IMG")
    @Lob
    private byte[] pxiImg;
    @NotNull
    @Column(name = "PXI_ORDEM")
    private Integer pxiOrdem;

    public ProdutoxImagem() {
    }

    public ProdutoxImagemPK getProdutoxImagemPK() {
        return produtoxImagemPK;
    }

    public void setProdutoxImagemPK(ProdutoxImagemPK produtoxImagemPK) {
        this.produtoxImagemPK = produtoxImagemPK;
    }

    public byte[] getPxiImg() {
        return pxiImg;
    }

    public void setPxiImg(byte[] pxiImg) {
        this.pxiImg = pxiImg;
    }

    public Integer getPxiOrdem() {
        return pxiOrdem;
    }

    public void setPxiOrdem(Integer pxiOrdem) {
        this.pxiOrdem = pxiOrdem;
    }

    public String getImgBase64() {
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(pxiImg);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.produtoxImagemPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutoxImagem other = (ProdutoxImagem) obj;
        return Objects.equals(this.produtoxImagemPK, other.produtoxImagemPK);
    }
}

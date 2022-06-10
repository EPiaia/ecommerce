package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "CONFIGURACAO")
public class Configuracao implements Serializable {

    @Id
    @Column(name = "CFG_ID")
    private Integer cfgId;
    @NotNull
    @Column(name = "CFG_CTRL_FRETE")
    private boolean cfgCtrlFrete = false;
    @Column(name = "CFG_FRETEPAD")
    private BigDecimal cfgFretePad;
    @Column(name = "CFG_VLRMIN_FRETE")
    private BigDecimal cfgVlrMinFrete;

    public Configuracao() {
    }

    public Integer getCfgId() {
        return cfgId;
    }

    public void setCfgId(Integer cfgId) {
        this.cfgId = cfgId;
    }

    public boolean isCfgCtrlFrete() {
        return cfgCtrlFrete;
    }

    public void setCfgCtrlFrete(boolean cfgCtrlFrete) {
        this.cfgCtrlFrete = cfgCtrlFrete;
    }

    public BigDecimal getCfgFretePad() {
        return cfgFretePad;
    }

    public void setCfgFretePad(BigDecimal cfgFretePad) {
        this.cfgFretePad = cfgFretePad;
    }

    public BigDecimal getCfgVlrMinFrete() {
        return cfgVlrMinFrete;
    }

    public void setCfgVlrMinFrete(BigDecimal cfgVlrMinFrete) {
        this.cfgVlrMinFrete = cfgVlrMinFrete;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.cfgId);
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
        final Configuracao other = (Configuracao) obj;
        return Objects.equals(this.cfgId, other.cfgId);
    }
}

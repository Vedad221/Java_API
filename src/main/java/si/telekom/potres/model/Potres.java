package si.telekom.potres.model;

import lombok.Setter;

public final class Potres {
    @Setter
    private Vreme vreme;
    private final String najblizjiKraj;
    private final String geoLokacija;
    private final double globina;

    public Potres( String najblizjiKraj, String geoLokacija, double globina) {
        this.najblizjiKraj = najblizjiKraj;
        this.geoLokacija = geoLokacija;
        this.globina = globina;
    }


    public Vreme getVreme() {
        return this.vreme;
    }

    public String getNajblizjiKraj() {
        return this.najblizjiKraj;
    }

    public String getGeoLokacija() {
        return this.geoLokacija;
    }

    public double getGlobina() {
        return this.globina;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Potres)) return false;
        final Potres other = (Potres) o;
        final Object this$vreme = this.getVreme();
        final Object other$vreme = other.getVreme();
        if (this$vreme == null ? other$vreme != null : !this$vreme.equals(other$vreme)) return false;
        final Object this$najblizjiKraj = this.getNajblizjiKraj();
        final Object other$najblizjiKraj = other.getNajblizjiKraj();
        if (this$najblizjiKraj == null ? other$najblizjiKraj != null : !this$najblizjiKraj.equals(other$najblizjiKraj))
            return false;
        final Object this$geoLokacija = this.getGeoLokacija();
        final Object other$geoLokacija = other.getGeoLokacija();
        if (this$geoLokacija == null ? other$geoLokacija != null : !this$geoLokacija.equals(other$geoLokacija))
            return false;
        if (Double.compare(this.getGlobina(), other.getGlobina()) != 0) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $vreme = this.getVreme();
        result = result * PRIME + ($vreme == null ? 43 : $vreme.hashCode());
        final Object $najblizjiKraj = this.getNajblizjiKraj();
        result = result * PRIME + ($najblizjiKraj == null ? 43 : $najblizjiKraj.hashCode());
        final Object $geoLokacija = this.getGeoLokacija();
        result = result * PRIME + ($geoLokacija == null ? 43 : $geoLokacija.hashCode());
        final long $globina = Double.doubleToLongBits(this.getGlobina());
        result = result * PRIME + (int) ($globina >>> 32 ^ $globina);
        return result;
    }

    public String toString() {
        return "Potres(vreme=" + this.getVreme() + ", najblizjiKraj=" + this.getNajblizjiKraj() + ", geoLokacija=" + this.getGeoLokacija() + ", globina=" + this.getGlobina() + ")";
    }
}


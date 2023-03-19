package com.movieboxpro.android.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes3.dex */
public class IpInfoModel {
    private String ip;
    private LocationBean location;

    public String getIp() {
        return this.ip;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public LocationBean getLocation() {
        return this.location;
    }

    public void setLocation(LocationBean locationBean) {
        this.location = locationBean;
    }

    /* loaded from: classes3.dex */
    public static class NamesBean {

        /* renamed from: de  reason: collision with root package name */
        private String f54de;
        private String en;
        private String es;
        private String fr;
        private String ja;
        @SerializedName("pt-BR")
        private String ptBR;
        private String ru;
        @SerializedName("zh-CN")
        private String zhCN;

        public String getDe() {
            return this.f54de;
        }

        public void setDe(String str) {
            this.f54de = str;
        }

        public String getEn() {
            return this.en;
        }

        public void setEn(String str) {
            this.en = str;
        }

        public String getEs() {
            return this.es;
        }

        public void setEs(String str) {
            this.es = str;
        }

        public String getFr() {
            return this.fr;
        }

        public void setFr(String str) {
            this.fr = str;
        }

        public String getJa() {
            return this.ja;
        }

        public void setJa(String str) {
            this.ja = str;
        }

        public String getPtBR() {
            return this.ptBR;
        }

        public void setPtBR(String str) {
            this.ptBR = str;
        }

        public String getRu() {
            return this.ru;
        }

        public void setRu(String str) {
            this.ru = str;
        }

        public String getZhCN() {
            return this.zhCN;
        }

        public void setZhCN(String str) {
            this.zhCN = str;
        }
    }

    /* loaded from: classes3.dex */
    public static class LocationBean {
        private ContinentBean continent;
        private CountryBean country;
        private RegisteredCountryBean registered_country;
        private List<SubdivisionsBean> subdivisions;

        public List<SubdivisionsBean> getSubdivisions() {
            return this.subdivisions;
        }

        public void setSubdivisions(List<SubdivisionsBean> list) {
            this.subdivisions = list;
        }

        public ContinentBean getContinent() {
            return this.continent;
        }

        public void setContinent(ContinentBean continentBean) {
            this.continent = continentBean;
        }

        public CountryBean getCountry() {
            return this.country;
        }

        public void setCountry(CountryBean countryBean) {
            this.country = countryBean;
        }

        public RegisteredCountryBean getRegistered_country() {
            return this.registered_country;
        }

        public void setRegistered_country(RegisteredCountryBean registeredCountryBean) {
            this.registered_country = registeredCountryBean;
        }

        /* loaded from: classes3.dex */
        public static class ContinentBean {
            private String code;
            private int geoname_id;
            private NamesBean names;

            public String getCode() {
                return this.code;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int i) {
                this.geoname_id = i;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean namesBean) {
                this.names = namesBean;
            }
        }

        /* loaded from: classes3.dex */
        public static class CountryBean {
            private int geoname_id;
            private String iso_code;
            private NamesBean names;

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int i) {
                this.geoname_id = i;
            }

            public String getIso_code() {
                return this.iso_code;
            }

            public void setIso_code(String str) {
                this.iso_code = str;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean namesBean) {
                this.names = namesBean;
            }
        }

        /* loaded from: classes3.dex */
        public static class RegisteredCountryBean {
            private int geoname_id;
            private String iso_code;
            private NamesBean names;

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int i) {
                this.geoname_id = i;
            }

            public String getIso_code() {
                return this.iso_code;
            }

            public void setIso_code(String str) {
                this.iso_code = str;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean namesBean) {
                this.names = namesBean;
            }
        }

        /* loaded from: classes3.dex */
        public static class SubdivisionsBean {
            private int geoname_id;
            private String iso_code;
            private NamesBean names;

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int i) {
                this.geoname_id = i;
            }

            public String getIso_code() {
                return this.iso_code;
            }

            public void setIso_code(String str) {
                this.iso_code = str;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean namesBean) {
                this.names = namesBean;
            }
        }
    }
}

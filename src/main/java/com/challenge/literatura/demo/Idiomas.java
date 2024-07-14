package com.challenge.literatura.demo;

public enum Idiomas {
    es("Español"),
    en("Inglés"),
    fr("Francés"),
    pt("Portugués"),
    nd("No disponible"),
    ;

    private String idiomaCompleto;

    Idiomas(String idiomaCompleto){
        this.idiomaCompleto=idiomaCompleto;
    }

    public static Idiomas stringToEnum(String idioma){
        for(Idiomas item:Idiomas.values()){
            if(item.name().equalsIgnoreCase(idioma)){
                return item;
            }
        }
        return nd;
    }

    public static void listarIdiomas(){
        for (Idiomas idioma:Idiomas.values()){
            System.out.println(idioma.name()+" - "+idioma.getIdiomaCompleto());
        }
    }

    public String getIdiomaCompleto() {
        return idiomaCompleto;
    }
}
package org.mw.lang;

/**
 * http://stackoverflow.com/questions/184710/what-is-the-difference-between-a-deep-copy-and-a-shallow-copy
 *
 */
public class Language implements Cloneable{

    String name;
    public Language(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

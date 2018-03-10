package fr.epsi.myEpsi.controlers.jmx;

public interface IConsoleMBean {

        public String getNom();

        public int getValeur();
        public void setValeur(int valeur);

        public void rafraichir();

}

public class Carta {
	public static final String[] valoriCarte=new String[]{"due", "quattro","cinque","sei","sette","donna","cavallo","re","tre","asso"};
	private String carta="";
	public Carta(String carta) {
		this.carta=carta;
	}
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	public int valoreCarta() {
		if(carta.contains("due") || carta.contains("quattro") || carta.contains("cinque") || carta.contains("sei") || carta.contains("sette"))
				return 0;
		else if(carta.contains("donna"))
			return 2;
		else if(carta.contains("cavallo"))
			return 3;
		else if(carta.contains("tre"))
			return 10;
		else if(carta.contains("asso"))
			return 11;
		return 4;
	}
	public int cercaCarta() {
		for(int i=0;i<valoriCarte.length;i++) {
			if(carta.equals(valoriCarte[i]+" bastoni"))
				return i;
			if(carta.equals(valoriCarte[i]+" coppe"))
				return i;
			if(carta.equals(valoriCarte[i]+" spade"))
				return i;
			if(carta.equals(valoriCarte[i]+" denari"))
				return i;
		}
		return -1;
	}
	public String getFigura() {
		String figura="";
		int i=carta.length()-1;
		while(carta.charAt(i)!=32) {
			figura+=carta.charAt(i);
			i--;
		}
		return inverti(figura);
	}
	private String inverti(String s) {
		String s1="";
		for(int i=s.length()-1;i>=0;i--)
			s1+=s.charAt(i);
		return s1;
	}
}

import java.util.Random;

public class Celula
{
	private static int[][] cel;

	public Celula(int lin, int col)
	{
		cel = new int[lin][col];
	}

	public void preenche_cel()
	{
		for(int lin = 0; lin < cel.length; lin++) {
			for(int col = 0; col < cel.length; col++) {
				cel[lin][col] = 0;
			}
		}
		Random rand = new Random();
		for(int i = 0; i < cel.length * 7; i++)
			cel[rand.nextInt(cel.length)][rand.nextInt(cel.length)] = 1;

		cel[0][0] = 0;
	}

	public void show_cel()
	{
		for(int lin = 0; lin < cel.length; lin++) {
			for(int col = 0; col < cel.length; col++) {
				System.out.print(cel[lin][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void percorre(int[][] cel, int lin, int col)
	{
		if(cel[lin][col] == 1)
			return;

		if(cel[lin][col] == 0) {
			cel[lin][col] = 2;
			percorre(cel, lin, col);
		}

		if(lin-1 >= 0 && cel[lin-1][col] == 0) // cima
			percorre(cel, --lin, col);

		if(col+1 < cel.length && cel[lin][col+1] == 0) // direita
			percorre(cel, lin, ++col);

		if(lin+1 < cel.length && cel[lin+1][col] == 0) // baixo
			percorre(cel, ++lin, col);

		if(col-1 >= 0 && cel[lin][col-1] == 0) // esquerda
			percorre(cel, lin, --col);
	}

	public static void main (String args[])
	{
		Celula c = new Celula(20, 20);

		c.preenche_cel();
		c.show_cel();
		c.percorre(cel, 0, 0);
		c.show_cel();
	}
}

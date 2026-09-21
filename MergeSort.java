public class MergeSort
{
    // Ordena somente o trecho do vetor que vai de inicio ate fim.
    public static void ordene (int[] vetor, int inicio, int fim)
    {
        // Se sobrou apenas um elemento, esse trecho ja esta ordenado.
        if (inicio>=fim)
            return;

        int meio = (inicio+fim)/2;

        // Divide o problema em duas metades.
        MergeSort.ordene (vetor, inicio, meio);
        MergeSort.ordene (vetor, meio+1, fim);

        // Junta as duas metades ja ordenadas.
        MergeSort.intercale (vetor, inicio, meio, fim);
    }

    // Intercala dois trechos consecutivos e ja ordenados:
    // inicio..meio e meio+1..fim.
    public static void intercale (int[] vetor, int inicio, int meio, int fim)
    {
        int[] auxiliar = new int[fim-inicio+1];

        int i=inicio;
        int j=meio+1;
        int k=0;

        // Compara o primeiro elemento ainda nao utilizado de cada metade.
        while (i<=meio && j<=fim)
        {
            if (vetor[i]<=vetor[j])
            {
                auxiliar[k]=vetor[i];
                i++;
            }
            else
            {
                auxiliar[k]=vetor[j];
                j++;
            }

            k++;
        }

        // Se sobraram elementos na primeira metade, copia todos.
        while (i<=meio)
        {
            auxiliar[k]=vetor[i];
            i++;
            k++;
        }

        // Se sobraram elementos na segunda metade, copia todos.
        while (j<=fim)
        {
            auxiliar[k]=vetor[j];
            j++;
            k++;
        }

        // Devolve o trecho ordenado para o vetor original.
        for (i=0; i<auxiliar.length; i++)
            vetor[inicio+i]=auxiliar[i];
    }
}

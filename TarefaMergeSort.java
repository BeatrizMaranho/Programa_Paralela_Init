public class TarefaMergeSort extends Thread
{
    private int[] vetor;
    private int inicio;
    private int fim;

    public TarefaMergeSort (int[] vetor, int inicio, int fim) throws Exception
    {
        if (vetor==null)
            throw new Exception ("Vetor ausente");

        this.vetor  = vetor;
        this.inicio = inicio;
        this.fim    = fim;
    }

    @Override
    public void run ()
    {
        // Cada thread executa o MergeSort apenas no seu pedaco do vetor.
        MergeSort.ordene (this.vetor, this.inicio, this.fim);
    }
}

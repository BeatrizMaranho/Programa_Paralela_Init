public class Programa
{
    private static void mostreVetor (int[] vetor)
    {
        System.out.print ("[");

        for (int i=0; i<vetor.length; i++)
        {
            System.out.print (vetor[i]);

            if (i<vetor.length-1)
                System.out.print (", ");
        }

        System.out.println ("]");
    }

    public static void main (String[] args)
    {
        try
        {
            System.out.print ("Digite o tamanho do vetor: ");
            int tamanho = Teclado.getUmInt ();

            while (tamanho<=0)
            {
                System.out.println ("O tamanho precisa ser maior que zero.");
                System.out.print ("Digite o tamanho do vetor: ");
                tamanho = Teclado.getUmInt ();
            }

            int[] vetor = new int[tamanho];

            System.out.println ();
            System.out.println ("Como deseja preencher o vetor?");
            System.out.println ("1 - Automaticamente, com valores aleatorios");
            System.out.println ("2 - Manualmente");
            System.out.print   ("Opcao: ");
            int opcao = Teclado.getUmInt ();

            while (opcao!=1 && opcao!=2)
            {
                System.out.print ("Opcao invalida. Digite 1 ou 2: ");
                opcao = Teclado.getUmInt ();
            }

            if (opcao==1)
            {
                for (int i=0; i<vetor.length; i++)
                    vetor[i] = (int)(Math.random()*1000);
            }
            else
            {
                for (int i=0; i<vetor.length; i++)
                {
                    System.out.print ("vetor["+i+"] = ");
                    vetor[i] = Teclado.getUmInt ();
                }
            }

            System.out.println ();
            if (vetor.length<=100)
            {
                System.out.println ("Vetor antes da ordenacao:");
                Programa.mostreVetor (vetor);
            }
            else
                System.out.println ("Vetor muito grande: ele nao sera exibido antes da ordenacao.");

            int qtdProcessadores = Runtime.getRuntime().availableProcessors ();

            // Se o vetor for menor que a quantidade de processadores,
            // nao faz sentido criar threads para pedacos vazios.
            int qtdThreads = qtdProcessadores;
            if (qtdThreads>vetor.length)
                qtdThreads=vetor.length;

            System.out.println ();
            System.out.println ("Processadores disponiveis: "+qtdProcessadores);
            System.out.println ("Threads que serao criadas: "+qtdThreads);

            TarefaMergeSort[] tarefas = new TarefaMergeSort[qtdThreads];
            int[] inicios = new int[qtdThreads];
            int[] fins    = new int[qtdThreads];

            int tamanhoBase = vetor.length/qtdThreads;
            int resto       = vetor.length%qtdThreads;
            int inicio      = 0;

            // Divide o vetor em partes. Quando a divisao nao for exata,
            // as primeiras threads recebem um elemento a mais.
            for (int i=0; i<qtdThreads; i++)
            {
                int tamanhoDaParte = tamanhoBase;

                if (i<resto)
                    tamanhoDaParte++;

                int fim = inicio+tamanhoDaParte-1;

                inicios[i]=inicio;
                fins[i]=fim;
                tarefas[i] = new TarefaMergeSort (vetor, inicio, fim);

                inicio=fim+1;
            }

            // Inicia todas as threads.
            for (int i=0; i<qtdThreads; i++)
                tarefas[i].start ();

            // A main espera todas as threads terminarem.
            for (int i=0; i<qtdThreads; i++)
                tarefas[i].join ();

            // Neste ponto cada pedaco esta ordenado, mas ainda precisamos
            // intercalar os pedacos para obter um unico vetor ordenado.
            for (int i=1; i<qtdThreads; i++)
                MergeSort.intercale (vetor, 0, inicios[i]-1, fins[i]);

            System.out.println ();
            if (vetor.length<=100)
            {
                System.out.println ("Vetor depois da ordenacao:");
                Programa.mostreVetor (vetor);
            }
            else
                System.out.println ("Ordenacao finalizada. O vetor nao foi exibido por ser muito grande.");
        }
        catch (Exception erro)
        {
            System.err.println (erro.getMessage());
        }
    }
}

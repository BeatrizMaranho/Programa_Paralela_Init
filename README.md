# Programa Paralela - MergeSort com Threads

Projeto inicial para o trabalho de Programacao Paralela.

A proposta implementada aqui segue a descricao passada em aula:

- pedir o tamanho do vetor;
- permitir preenchimento automatico ou manual;
- ordenar usando MergeSort recursivo;
- descobrir a quantidade de processadores disponiveis;
- dividir o vetor em partes;
- criar uma thread para cada parte;
- cada thread executa MergeSort no seu trecho;
- a `main` espera todas as threads com `join()`;
- depois, os trechos ordenados sao intercalados ate formar o vetor final ordenado.

## Arquivos

- `Programa.java`: entrada de dados, divisao do vetor, criacao das threads e juncao final.
- `TarefaMergeSort.java`: representa uma thread que ordena um trecho do vetor.
- `MergeSort.java`: implementa a recursao do MergeSort e o metodo de intercalacao.
- `Teclado.java`: leitura simples pelo teclado, mantendo o estilo usado nas aulas.

## Como compilar

```bash
javac Programa.java TarefaMergeSort.java MergeSort.java Teclado.java
```

## Como executar

```bash
java Programa
```

## Observacao importante para a aula

Esta e uma versao inicial baseada na explicacao oral do trabalho. Se o enunciado oficial trouxer alguma exigencia diferente na aula, a estrutura foi mantida simples justamente para facilitar alteracoes.

Um ponto que pode precisar ser confirmado com o professor e a etapa final: depois que cada thread ordena seu proprio trecho, os trechos ainda precisam ser intercalados para que o vetor inteiro fique ordenado. Nesta versao, essa intercalacao final e feita pela `main`, depois dos `join()`.

package com.example.map.smartcity;

import java.util.Stack;


public class TSM

{

    private int numberOfNodes;

    private Stack<Integer> stack;

    int[] res;

    public TSM(double d[][], int number_of_nodes)

    {

        int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
        stack = new Stack<Integer>();
        for (int i = 1; i <= number_of_nodes; i++) {

            for (int j = 1; j <= number_of_nodes; j++) {

                adjacency_matrix[i][j] = (int) d[i-1][j-1];

            }

        }
        res = new int[number_of_nodes];
        res = tsp(adjacency_matrix, number_of_nodes);

    }


    public int[] tsp(int adjacencyMatrix[][], int k)

    {
        int l = 0;
        int res[] = new int[k];

        numberOfNodes = adjacencyMatrix[1].length - 1;

        int[] visited = new int[numberOfNodes + 1];

        visited[1] = 1;

        stack.push(1);

        int element, dst = 0, i;

        int min = Integer.MAX_VALUE;

        boolean minFlag = false;

//        System.out.print(1 + "\t");
        res[l++] = 1;

        while (!stack.isEmpty())

        {

            element = stack.peek();

            i = 1;

            min = Integer.MAX_VALUE;

            while (i <= numberOfNodes)

            {

                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)

                {

                    if (min > adjacencyMatrix[element][i])

                    {

                        min = adjacencyMatrix[element][i];

                        dst = i;

                        minFlag = true;

                    }

                }

                i++;

            }

            if (minFlag)

            {

                visited[dst] = 1;

                stack.push(dst);

//                System.out.print(dst + "\t");
                res[l++] = dst;

                minFlag = false;

                continue;

            }

            stack.pop();

        }

        return res;
    }


}
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class App<T extends Comparable<T>> 
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to generate a random array(1) or read from a file(2)");
        Integer input = scan.nextInt();

        if (input == 1) 
        {
            Integer[] arr = generateRandomArray(10);
            System.out.println("Before Sort:    " + Arrays.toString(arr));

            App<Integer> sort = new App<>();

            sort.bubbleSort(arr);
            System.out.println("Bubble Sort:    " + Arrays.toString(arr));

            sort.mergeSort(arr);
            System.out.println("Merge Sort:     " + Arrays.toString(arr));

            String fp = "array.txt";
            try {
                writeArrayToFile(arr, fp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (input == 2) 
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What is the file name?");
            String fileName = scanner.nextLine();

            String fp = fileName;

            try
            {
                Integer[] arr = readFileToArray(fp);
                for (int num : arr) 
                {
                    System.out.println(num);
                }

                System.out.println("Before Sort:    " + Arrays.toString(arr));

                App<Integer> sort = new App<>();

                sort.bubbleSort(arr);
                System.out.println("Bubble Sort:    " + Arrays.toString(arr));

                sort.mergeSort(arr);
                System.out.println("Merge Sort:     " + Arrays.toString(arr));

                String fp2 = "array2.txt";
                try {
                    writeArrayToFile(arr, fp2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
            scanner.close();

            /*System.out.println("Before Sort:    " + Arrays.toString(arr));

            App<Integer> sort = new App<>();

            sort.bubbleSort(arr);
            System.out.println("Bubble Sort:    " + Arrays.toString(arr));

            sort.mergeSort(arr);
            System.out.println("Merge Sort:     " + Arrays.toString(arr));

            String fp2 = "array.txt";
            try {
                writeArrayToFile(arr, fp);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        else
        {
            System.out.println("Invalid input, please try again");
        }
        scan.close();
    }

        /*Integer[] arr = generateRandomArray(10);
        System.out.println("Before Sort:    " + Arrays.toString(arr));

        App<Integer> sort = new App<>();

        sort.bubbleSort(arr);
        System.out.println("Bubble Sort:    " + Arrays.toString(arr));

        sort.mergeSort(arr);
        System.out.println("Merge Sort:     " + Arrays.toString(arr));

        String fp = "array.txt";
        try {
            writeArrayToFile(arr, fp);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    

    public static Integer[] generateRandomArray(int size)
    {
        Integer[] arr = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public void bubbleSort(T[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - 1; j++)
            {
                if (arr[j].compareTo(arr[j + 1]) > 0)
                {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void mergeSort(T[] arr)
    {
        if (arr.length <= 1)
        {
            return;
        }
        int mid = arr.length / 2;
        T[] left = Arrays.copyOfRange(arr, 0, mid);
        T[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private void merge(T[] arr, T[] left, T[] right)
    {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
        {
            if (left[i].compareTo(right[j]) <= 0)
            {
                arr[k++] = left[i++];
            }
            else
            {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length)
        {
            arr[k++] = left[i++];
        }
        while (j < right.length)
        {
            arr[k++] = right[j++];
        }
    }

    public static void writeArrayToFile(Integer[] arr, String fp) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fp));

        for(int i = 0; i < arr.length; i++)
        {
            writer.write(arr[i] + " ");
            writer.newLine();
        }
        writer.close();
    }

    public static Integer[] readFileToArray(String fp) throws IOException
    {
        Integer[] arr = null;
        int count = 0;

        BufferedReader reader = new BufferedReader(new FileReader(fp));
        while(reader.readLine() != null)
        {
            count++;
        }
        reader.close();

        reader = new BufferedReader(new FileReader(fp));
        arr = new Integer[count];
        
        String line;
        int i = 0;

        while ((line = reader.readLine()) != null)
        {
            int num = Integer.parseInt(line.trim());
            arr[i++] = num;
        }
        reader.close();
        return arr;
    }
}

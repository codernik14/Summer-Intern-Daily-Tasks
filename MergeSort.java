public class MergeSort {
    public static void main(String args[])
    {
        int arr[] = {5,7,78,65,2345,432,54,765,67,543};
        int n = arr.length;

        MergeSort mergesort = new MergeSort();

        mergesort.sort(arr, 0, n-1);
        for(int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    void merge(int arr[], int left, int mid, int right)
    {
        int p = mid + 1 -left;
        int q = right - mid;

        int arr1[] = new int[p];
        int arr2[] = new int[q];

        for(int i = 0;i < p;i++)
        {
            arr1[i] = arr[left+i];
        }

        for(int i = 0;i < q;i++)
        {
            arr2[i] = arr[mid+i+1];
        }

        int i = 0, j = 0;
        int k = left;
        while(i<p && j<q)
        {
            if(arr1[i] < arr2[j])
            {
                arr[k] = arr1[i];
                i++;
            }
            else
            {
                arr[k] = arr2[j];
                j++;
            }
            k++;
        }

        while(i<p)
        {
            arr[k] = arr1[i];
            i++;
            k++;
        }

        while(j<q)
        {
            arr[k] = arr2[j];
            j++;
            k++;
        }
    }

    void sort(int arr[], int left, int right)
    {
        if(left<right)
        {
            int mid = left + (right - left) / 2;
//            System.out.println(left + " " + mid + " " + right);
            sort(arr,left,mid);
            sort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }
}

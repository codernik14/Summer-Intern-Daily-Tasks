public class SegTree {
    int[] st;
    SegTree(int n)
    {
        st = new int[4*n];
    }

    void build(int[] arr, int left, int right, int ind)
    {
        if(left == right)
        {
            st[ind] = arr[left];
        }
        else
        {
            int mid = left + (right-left)/2;
            build(arr,left,mid,ind*2);
            build(arr,mid+1, right,ind*2+1);
            st[ind] = st[ind*2] + st[ind*2+1];
        }
    }

    int query(int l,int r, int left, int right, int ind)
    {
        if(l>r)
        {
            return 0;
        }
        else if (l == left && r == right)
        {
            return st[ind];
        }
        else
        {
            int mid = left + (right - left)/2;

            int val1 = query(l, Math.min(mid,r), left, mid, ind*2);
            int val2 = query(Math.max(l, mid+1), r, mid+1, right, ind*2+1);
            return val1 + val2;
        }
    }

    void update(int index, int val, int left, int right, int ind)
    {
        if(left == right)
        {
            st[ind] = val;
        }
        else
        {
            int mid = left + (right-left)/2;
            if(index <= mid)
            {
                update(index, val, left, mid, ind*2);
            }
            else
            {
                update(index,val,mid+1,right,ind*2+1);
            }
            st[ind] = st[ind*2] + st[ind*2+1];
        }
    }

    public static void main(String args[])
    {
        int arr[] = {1,2,3,4,5,6,7,8,9,10};
        int n = arr.length;
        SegTree tree = new SegTree(n);
        tree.build(arr,0,n-1,1);
        System.out.println("Before Update " + tree.query(1,3,0,n-1,1));
        tree.update(1,10,0,n-1,1);
        System.out.println("After Update " + tree.query(1,3,0,n-1,1));
    }
}

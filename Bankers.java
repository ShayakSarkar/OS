import java.util.*;

public class Bankers{
	public static void printArray(int[][] arr,int r,int c){
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++)
				System.out.print(arr[i][j]+" ");
			System.out.println();
		}
	}
	public static void printArray(int[] arr){
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	public static boolean calculateNeedMatrix(int[][] currentNeed,int[][] maxNeed,int[][] allocation,int noProc,int noResources){
		for(int i=0;i<noProc;i++){
			for(int j=0;j<noResources;j++){
				currentNeed[i][j]=maxNeed[i][j]-allocation[i][j];
				if(currentNeed[i][j]<0){
					System.out.println("Safe sequence does not exist");
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isOkay(int[] availability,int[] need){
		for(int i=0;i<need.length;i++)
			if(need[i]>availability[i])
				return false;
		return true;
	}
	public static void generateSafeSeq(int[][] currentNeed,int[] availability,int[][] allocation,int noProc,int noResources){
		int ctr=0;
		int flg=0;	// Number of iterations without selection. When equal to available resources, no safe seq exists
		int procAvailable=noProc;
		boolean[] selected=new boolean[noProc];
		for(int i=0;ctr<noProc;i=(i+1)%noProc){
			if(flg>=procAvailable && procAvailable!=0){
				System.out.println("Value of flg: "+flg);
				System.out.println("Safe Sequence no available");
				return;
			}
			if(selected[i])
				continue;
			if(isOkay(availability,currentNeed[i])){
				System.out.print("P"+i+", ");
				selected[i]=true;
				procAvailable--;
				flg=0;
				for(int j=0;j<noResources;j++)
					availability[j]+=allocation[i][j];
				continue;
			}
			flg++;
		}
	}

	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		System.out.print("Enter the number of processes: ");
		int noProc=in.nextInt();
		System.out.print("Enter the number of resources: ");
		int noResources=in.nextInt();

		int maxNeed[][]=new int[noProc][noResources];
		int availability[]=new int[noResources];
		int allocation[][]=new int[noProc][noResources];
		int currentNeed[][]=new int[noProc][noResources];

		System.out.println("Enter the max need of the processes: ");
		for(int i=0;i<noProc;i++){
			System.out.println("Enter the need of process "+i+": ");
			for(int j=0;j<noResources;j++){
				System.out.println("Enter for resource "+j+": ");
				maxNeed[i][j]=in.nextInt();
			}
		}

		System.out.println("Enter the availability of the resources: ");

		for(int i=0;i<noResources;i++){
			System.out.print("Enter for resource "+i+": ");
			availability[i]=in.nextInt();
		}
		System.out.println("Enter the current allocation of resources: ");

		for(int i=0;i<noProc;i++){
			System.out.println("Enter for process "+i+": ");
			for(int j=0;j<noResources;j++){
				System.out.print("Enter for resource "+j+": ");
				allocation[i][j]=in.nextInt();
			}
		}

		calculateNeedMatrix(currentNeed,maxNeed,allocation,noProc,noResources);
		printArray(currentNeed,noProc,noResources);
		generateSafeSeq(currentNeed,availability,allocation,noProc,noResources);
	}
}

			


		



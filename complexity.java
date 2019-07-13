// Program to Calculate Complexity


import java.util.*;
import java.lang.*;
import java.io.*;

class SwitchComplexity {
    static float log2n(float N){
        return (N>1)?1+log2n(N/2):0;
    }
    
    static float factorial(float n) 
    { 
        if (n == 0) 
          return 1; 
          
        return n*factorial(n-1); 
    } 
    
public static void main (String[] args) {
Scanner sc=new Scanner(System.in);
float N=sc.nextFloat();        // no of inlets
float c=sc.nextFloat();      //no. of channels
float k=sc.nextFloat(); 
float L=sc.nextFloat(); 
float p=sc.nextFloat();        // occupancy probability	
float p1=sc.nextFloat();
   float p2=sc.nextFloat();
   	String s=sc.next();
// We take p1 and p2 as only 1 and 0
// p1=1 for non blocking and p1=0 for blocking
// p2=1 for lee method and p2-0 for jacobian method
        
   float block_complexity=0;
   float  blocking_probability=0;
   float N1=0;

String st="STS";   //string to read type of switch
String st1="TST";
String st2="S";
String st3="SSS";
String st4="TSSST";
String st5="TSSSST";
float n=0 ;


// for S type of  Switch
if(s.equals(st2)){
   if(p1==1){
       block_complexity=N*(N-1);
   }
   else if(p1==0)
   block_complexity=N*(N-1)/2;
   System.out.println("block_complexity ="+ block_complexity);
}


// for SSS switch
if(s.equals(st3)){
   N1 = 4*N*((int)Math.sqrt(2*N)-1);
    block_complexity = N1 ;
    System.out.println("block_complexity ="+ block_complexity);
}



// for TSSST switch
float alpha=0,beta=0,p_d_switch=0, p_dd_switch =0,N_x=0,NB_x=0,NB_t=0;
float NB_tc=0;
float bp=0;
if(s.equals(st4)){
   
 n =(float) Math.sqrt(N/2);
    alpha = L/c;
    beta = k/N; 
    p_d_switch = p/alpha;
    p_dd_switch = p_d_switch/beta;
   // bp = (int)Math.pow(1-((int)Math.pow((1-p_d_switch),2))*((int)Math.pow((1-((int)Math.pow((1-p_dd_switch),2)),k),L);
    N_x = 2*N*k+k*(float)Math.pow((N/n),2); 
    NB_x = 2*k*(N/n)*log2n(n)*L+k*(N/n)*L*log2n(N/n);
    NB_t = 2*N*c*8;
    NB_tc = 2*N*L*log2n(c); 
    block_complexity = N_x+((NB_x+NB_t+NB_tc)/100)  ;  
    System.out.println("block_complexity = "+ block_complexity);
   
   
}




//    for TSSSST  switch

float p1_sw=0,q1_sw=0,q2_switch =0,p2_switch =0;

if(s.equals(st5)){
alpha = L/c;
    beta = k/N;  
    p1_sw = (7/8)*(120/128)*p;
    q1_sw = 1-p/alpha;
    q2_switch = 1-(p/(beta*alpha));
    p2_switch = 2*(float)Math.pow(p1_sw,2)*(float)Math.pow(q1_sw,6)+16*(float)Math.pow(p1_sw,3)*(float)Math.pow(q1_sw,5)+50*(float)Math.pow(q1_sw,4)*(float)Math.pow(p1_sw,4)+52*(float)Math.pow(p1_sw,5)*(float)Math.pow(q1_sw,3)+28*(float)Math.pow(p1_sw,6)*(float)Math.pow(q1_sw,2)+8*(float)Math.pow(p1_sw,7)*q1_sw+(float)Math.pow(p1_sw,8);
    bp = (float)Math.pow((1-(1-p1_sw)*(1-p2_switch)*(1-p1_sw)),128);
    System.out.println("blocking probability is"+" "+bp);
}


// for TST switch

float N2=0,Nb2=0,Switch_Complex=0;

if(s.equals(st1)){
   float p_dash=0;
   N2 = N*N;
    Nb2=((2*N*c*8)+(2*N*L*((float)Math.ceil(log2n(c))))+(N*L*((float)Math.ceil(log2n(N)))));
    Switch_Complex = N2+(1/100)*Nb2;
    if(p2==1){
        beta=k/N;
        p_dash = p/beta;
        blocking_probability = (float)Math.pow((1-((float)Math.pow((1-p_dash),2))),k);
        
    }
    else if(p2==0){
        
        
         blocking_probability = ((float)Math.pow(factorial(n),2)*(float)Math.pow(p,k)*(float)Math.pow((2-p),(2*n-k)))/(factorial(k)*factorial(2*n-k));
    }
   System.out.println("blocking_probability ="+ blocking_probability);
   
}




// for STS switches

if(s.equals(st)){
   float  p_dash1=0;
   float Nx=0,Nb=0,Nt=0;
  if(p1==1){
       n =(float) Math.sqrt(N/2);
                k = 2*n-1;  //conditions for minimum complexity
  
  if(p2==1){
       beta = k/N;
        p_dash1 = p/beta;
        block_complexity =(float)Math.pow((1-((float)Math.pow((1-p_dash1),2))),k);  //blocking probability formulae
  }
  else if(p2==0){
    block_complexity= ((float)Math.pow(factorial(n),2)*(float)Math.pow(p,k)*(float)Math.pow((2-p),(2*n-k)))/(factorial(k)*factorial(2*n-k));
  }
 }
  else if(p1==0){
      if(k!=0){
          if(p2==1){
              beta = k/N;
        p_dash1 = p/beta;
        bp = (float)Math.pow((1-((float)Math.pow((1-p_dash1),2))),k);
              
          }
          else if(p2==0){
           bp   = ((float)Math.pow(factorial(n),2)*(float)Math.pow(p,k)*(float)Math.pow((2-p),(2*n-k)))/(factorial(k)*factorial(2*n-k));
          }
          
      }
 
  }
   Nx=2*k*N;  
   Nb=2*k*c*log2n(N)+k*c*8+k*c*log2n(c);
   Nt=Nx+(Nb/100);  //Total complexity of TST switches
   System.out.println("Total complexity of switch is"+" "+Nt);
}


}	

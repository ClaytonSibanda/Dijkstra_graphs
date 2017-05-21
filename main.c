//********************************************************************
//*                    EEE2046F C template                           *
//*==================================================================*
//* WRITTEN BY:    	                 		             *
//* DATE CREATED:                                                    *
//* MODIFIED:                                                        *
//*==================================================================*
//* PROGRAMMED IN: Eclipse Luna Service Release 1 (4.4.1)            *
//* DEV. BOARD:    UCT STM32 Development Board                       *
//* TARGET:	   STMicroelectronics STM32F051C6                    *
//*==================================================================*
//* DESCRIPTION:                                                     *
//*                                                                  *
//********************************************************************
// INCLUDE FILES
//====================================================================
#include "lcd_stm32f0.h"
#include "stm32f0xx.h"
#include <stdio.h>
#include "lcd_stm32f0.h"
//===================================================================
// SYMBOLIC CONSTANTS
//====================================================================
#define DELAY1 1500
#define DELAY2 1500

#define SW0 GPIO_IDR_0
#define SW1 GPIO_IDR_1
#define SW2 GPIO_IDR_2
#define SW3 GPIO_IDR_3
//====================================================================
// GLOBAL VARIABLES
//====================================================================
unsigned int bitpattern = 0b0000110011111111;
//====================================================================
// FUNCTION DECLARATIONS
//====================================================================
void init_GPIO(void);

void Delay(void);
char countUp(char);
char countDown(char);
// Interrupt handlers


//====================================================================
// MAIN FUNCTION
//====================================================================
void main (void)
{

	init_GPIO();
	char value = 1;
	char value1=0;
	while((GPIOA->IDR & SW0)!=0);
	int i=0;

	for(;;){
		if((GPIOA->IDR &SW2)==0) i=2;
		else if((GPIOA->IDR &SW1)==0)i=1;

		if(i!=2 && i!=1){
			value=countUp(value);
			value1=value;
		}


	if(i==2){

		value1=countDown(value1);
	}
	if(i==1){

		value1=countUp(value1);
	}

	//if(GPIOA->IDR){
//		value=countUp(value1);
//		}
	Delay();
	}


	//init_NVIC();
	//for(;;);	// Loop forever


}							// End of main


//====================================================================
// FUNCTION DEFINITIONS
//====================================================================
char countUp(char value){
	GPIOB->ODR=value;
	value++;
	return value;
}

char countDown(char value){
	GPIOB->ODR=value;
	value--;
	return value;
}



void init_GPIO(void)
 {
 //--------------------------------------------------------------------
 //GPIOA - Pushbuttons
 //--------------------------------------------------------------------
 // enable clock for push-buttons
 RCC->AHBENR |= RCC_AHBENR_GPIOAEN;
 // set pins A0-A3 to GPIO inputs, GPIOA_MODERx = 00
 GPIOA->MODER &= ~(GPIO_MODER_MODER0|
 GPIO_MODER_MODER1|
 GPIO_MODER_MODER2|
 GPIO_MODER_MODER3);
 // enable pull-up resistors, GPIOA_PUPDRx = 01
 GPIOA->PUPDR |= (GPIO_PUPDR_PUPDR0_0|
 GPIO_PUPDR_PUPDR1_0|
 GPIO_PUPDR_PUPDR2_0|
 GPIO_PUPDR_PUPDR3_0);

 // enable clock for push-buttons
  RCC->AHBENR |= RCC_AHBENR_GPIOBEN;
  // set pins B0-B7, B10-B11 to GPIO outputs, GPIOB_MODERx = 01
 GPIOB->MODER |= (GPIO_MODER_MODER0_0|
  GPIO_MODER_MODER1_0|
  GPIO_MODER_MODER2_0|
  GPIO_MODER_MODER3_0|
  GPIO_MODER_MODER4_0|
  GPIO_MODER_MODER5_0|
  GPIO_MODER_MODER6_0|
  GPIO_MODER_MODER7_0|
  GPIO_MODER_MODER10_0|
  GPIO_MODER_MODER11_0);

 //GPIOB->ODR = bitpattern;
  }
void Delay(void)
 {
 int i,j;
 for (i=0;i<=DELAY1;i++){
	 for(j=0;j<=DELAY2;j++){};
	 }
 }


//********************************************************************
// END OF PROGRAM
//********************************************************************

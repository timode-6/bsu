# Лабораторная работа №6
## Моделирование роботизированного трехканального обрабатывающего центра
### <ins>Исходные данные:</ins>
1. Обрабатывающий центр ключает промышленный робот, который распределяет поступающие детали в три однотипных канала их махнообработки.
2. Детали, поступающие в обрабатывающий центр, образуют поток ***Эрланга 2-го порядка с интенсивностью*** $\lambda$ дет/сек, а время механообработки детали имеет ***экспоненциальное распределение*** с математическим ожиданием $\mu$ сек.
3. Робот обслуживает деталь [a±$\beta$] сек (равномерные закон распределния) распределяя ее в свободный канал. Если все канали заняты, то деталь направляется в очередь, которая на данный момент является минимальной.
### Цель:
Разработать GPSSV - модель для анализа процесса функционирования обрабатывающего центра в течение одного часа.
<ins>Первоначальный перечень экспериментов:</ins> $\lambda$ = 0.1, $\mu$ =  25, a = 3, $\beta$ = 1.
### Реализация:
```sql
lambda 	EQU 0.1
alpha 	EQU 3
delta 	EQU 1
mu 	EQU 25
period 	EQU 3600

CHANNEL STORAGE 3	        			 								;  number of channels

;GENERATE        (EXPONENTIAL(1,0,lambda) + EXPONENTIAL(1,0,lambda) + EXPONENTIAL(1,0,lambda))	;  generate transaction in Erlang's stream of 2 order
GENERATE	      (GAMMA(1,0,lambda,3)) ; alternative way
QUEUE ROBOTQUEUE												;  create queue to robot (to obtain statistics only)
SEIZE ROBOT												;  lock robot
DEPART ROBOTQUEUE												;  remove transaction from queue
ADVANCE	      alpha,delta											;  lock robot on t from (a +- delta) seconds
RELEASE ROBOT												;  release robot
QUEUE CHANNELQUEUE												;  create queue to channel (to obtain statistics only)
ENTER CHANNEL												;  lock one free channel or wait
DEPART CHANNELQUEUE												;  remove transaction from queue
ADVANCE	      (EXPONENTIAL(1,0,mu))										;  lock channel on exponential(mu) seconds
LEAVE CHANNEL												;  release channel
TERMINATE	      0												;  remove transaction from queue

GENERATE        period                               								;  analysis period (1 h = 3600 sec)
TERMINATE       1                                  								;  minus one second
START           1                                  								;  start model
```
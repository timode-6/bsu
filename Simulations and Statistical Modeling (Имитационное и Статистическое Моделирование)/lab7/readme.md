# Лабораторная работа №7
## Моделирование процесса обслуживания коллектива пользователей
### <ins>Исходные данные:<ins>
1. Терминальный класс имеет ***m*** активных терминалов, которые обслуживает консультант-программист.
2. Пользователь требует обслуживания через 30±10 минут, причем время его обслуживания составляет 3±2 минуты.
3. Обслуживание пользователей осуществляется в порядке очереди без приоритетов.
4. Если в системе возникает сбой, то консультант-программист обслуживает пользователя и после этого устраняет сбой. Сбои возникают в системе через каждые a±$\alpha$ минут, а время их устраннеия b±$\beta$ минут (равномерный закон распределения)
### Цель:
Разработать GPSSV - модель процесса обслуживания коллектива пользователей в течение 8 часов. 
<ins>Первоначальные перечень экспериментов:</ins> ***m*** = 12, a = 90, $\alpha$ = 20, b = 9, $\beta$ = 3.
### Реализация:
```sql
terminals EQU 12
a 	EQU 90
alpha 	EQU 30
b 	EQU 9
bet 	EQU 3
period    EQU 480

		GENERATE		,,,terminals					; generate terminals (users)
WorkUntilSupport	ADVANCE		30,10							; worktime until need for support
		QUEUE MASTERQUEUE								; enter queue to master (to obtain statistics only)
		SEIZE MASTER									; lock master for support
		DEPART MASTERQUEUE								; remove query from queue
		ADVANCE		3,2     						; providing support
		RELEASE MASTER									; release master
		TRANSFER		,WorkUntilSupport				; return user to normal work

		GENERATE	      	a,alpha,,,1  					; generate failure
		QUEUE MASTERQUEUE								; enter queue to master with priority (to obtain statistics only)
		SEIZE MASTER									; lock master for repair failure
		DEPART MASTERQUEUE								; remove query from queue
		ADVANCE		b,bet   						; maintain failure
		RELEASE MASTER									; release master
		TERMINATE		0							; remove failure transaction from queue

		GENERATE         	period                               	;  analysis period (8 h = 480 minutes)
		TERMINATE        	1                                  	;  minus one minute
		START            	1                                  	;  start model

```
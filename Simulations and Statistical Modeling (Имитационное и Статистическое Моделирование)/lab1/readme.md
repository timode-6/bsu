# Лабораторная работа №1
Моделирование базовых случайных величин
## Условие
1. Осуществить моделирование `n = 100` реализация БСВ с помощью [мультипликативного конгруэнтного метода (МКМ)](https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%BD%D0%B5%D0%B9%D0%BD%D1%8B%D0%B9_%D0%BA%D0%BE%D0%BD%D0%B3%D1%80%D1%83%D1%8D%D0%BD%D1%82%D0%BD%D1%8B%D0%B9_%D0%BC%D0%B5%D1%82%D0%BE%D0%B4) с параметрами `a0`, `β`, `M = 2^31`.
2. Осуществить моделирование `n = 1000` реализация БСВ с помощью [метода Макларена-Марсальи](https://ru.wikipedia.org/wiki/%D0%93%D0%B5%D0%BD%D0%B5%D1%80%D0%B0%D1%82%D0%BE%D1%80_%D0%9C%D0%B0%D0%BA%D0%BB%D0%B0%D1%80%D0%B5%D0%BD%D0%B0_%E2%80%94_%D0%9C%D0%B0%D1%80%D1%81%D0%B0%D0%BB%D1%8C%D0%B8) (один датчик должен быть мультипликативно конгруэнтный (п.1), второй - на выбор). `K` - объем вспомогательной таблицы.
3. Проверить точность моделирования обоих датчиков (п.1 и п.2) с помощью [критерия согласия Колмогорова](https://ru.wikipedia.org/wiki/%D0%9A%D1%80%D0%B8%D1%82%D0%B5%D1%80%D0%B8%D0%B9_%D1%81%D0%BE%D0%B3%D0%BB%D0%B0%D1%81%D0%B8%D1%8F_%D0%9A%D0%BE%D0%BB%D0%BC%D0%BE%D0%B3%D0%BE%D1%80%D0%BE%D0%B2%D0%B0) и  [$\chi^{2}$-критерия Пирсона](https://ru.wikipedia.org/wiki/%D0%9A%D1%80%D0%B8%D1%82%D0%B5%D1%80%D0%B8%D0%B9_%D1%81%D0%BE%D0%B3%D0%BB%D0%B0%D1%81%D0%B8%D1%8F_%D0%9A%D0%BE%D0%BB%D0%BC%D0%BE%D0%B3%D0%BE%D1%80%D0%BE%D0%B2%D0%B0) с уровнем значимости `ε = 0.05`
## Результат
``````
pearson chi:
    Multiplicative congruent method: Power_divergenceResult(statistic=6.119999999999999, pvalue=0.7278506992125442)
    McLaren-Marsaglia method: Power_divergenceResult(statistic=4.56, pvalue=0.8708559358415972)

kolmogorov, sqrt(n)*dn
    Multiplicative congruent method: KstestResult(statistic=0.024141555260866893, pvalue=0.5959242851242297, statistic_location=0.14585844473913312, statistic_sign=1)
    McLaren-Marsaglia method: KstestResult(statistic=0.03369488661363723, pvalue=0.20167390541945118, statistic_location=0.3206948866136372, statistic_sign=-1)

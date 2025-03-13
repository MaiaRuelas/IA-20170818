#REGRESION LINEAL 
#Maia Paulina Ruelas Gutierrez
#La regresión lineal es una técnica estadística utilizada para modelar la relación entre una variable 
# dependiente (Y) y una o más variables independientes (X). El objetivo es encontrar una línea recta 
# que mejor ajuste los datos, permitiendo predecir el valor de la variable dependiente basada 
# en las variables independientes.

#Algoritmo que utiliza regresión lineal
import numpy as np
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt

# Datos de ejemplo (X: variable independiente, Y: variable dependiente)
X = np.array([[1], [2], [3], [4], [5]])  # Valores de entrada
Y = np.array([1.5, 3.0, 4.5, 6.0, 7.5])  # Valores reales de salida

# Crear el modelo de regresión lineal
modelo = LinearRegression()

# Entrenar el modelo (ajustar la línea a los datos)
modelo.fit(X, Y)

# Predecir valores con el modelo entrenado
predicciones = modelo.predict(X)

# Imprimir coeficientes
print(f"Pendiente (beta_1): {modelo.coef_[0]}")
print(f"Intersección (beta_0): {modelo.intercept_}")

# Graficar los datos originales y la línea de regresión
plt.scatter(X, Y, color='blue', label='Datos Originales')  # Puntos de los datos
plt.plot(X, predicciones, color='red', label='Línea de Regresión')  # Línea de regresión
plt.xlabel('Variable independiente (X)')
plt.ylabel('Variable dependiente (Y)')
plt.legend()
plt.show()

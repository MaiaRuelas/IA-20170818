# pip install tensorflow
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

# Datos de entrada (números del 0 al 9)
X = np.array([[i] for i in range(10)])  # Entradas

# Etiquetas: 0 si es impar, 1 si es par
y = np.array([[1 if i % 2 == 0 else 0] for i in range(10)])  # Salidas

# Crear el modelo
modelo = Sequential()
modelo.add(Dense(10, input_dim=1, activation='relu'))  # Capa oculta
modelo.add(Dense(1, activation='sigmoid'))  # Capa de salida

# Compilar el modelo
modelo.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

# Entrenar la red
modelo.fit(X, y, epochs=100, verbose=0)

# Probar con nuevos valores
nuevos = np.array([[10], [11], [25]])
predicciones = modelo.predict(nuevos)

# Mostrar resultados
for i, valor in enumerate(nuevos):
    print(f"{valor[0]} ➝ {'Par' if predicciones[i][0] > 0.5 else 'Impar'} (probabilidad: {predicciones[i][0]:.2f})")

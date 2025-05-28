pip install tensorflow matplotlib

import tensorflow as tf
from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Flatten
from tensorflow.keras.utils import to_categorical
import matplotlib.pyplot as plt

# 1. Cargar datos
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# 2. Normalizar los datos (0-255 a 0-1)
x_train = x_train / 255.0
x_test = x_test / 255.0

# 3. Codificación one-hot para las etiquetas
y_train_cat = to_categorical(y_train, 10)
y_test_cat = to_categorical(y_test, 10)

# 4. Construir modelo
model = Sequential([
    Flatten(input_shape=(28, 28)),     # Convierte imagen 28x28 en un vector de 784 elementos
    Dense(128, activation='relu'),     # Capa oculta con 128 neuronas y ReLU
    Dense(10, activation='softmax')    # Capa de salida para 10 clases
])

# 5. Compilar modelo
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# 6. Entrenar modelo
model.fit(x_train, y_train_cat, epochs=5, batch_size=32, validation_split=0.1)

# 7. Evaluar el modelo
test_loss, test_acc = model.evaluate(x_test, y_test_cat)
print(f'\nPrecisión en los datos de prueba: {test_acc:.2f}')

# 8. Probar con una imagen del conjunto de prueba
import numpy as np
imagen = x_test[0]
plt.imshow(imagen, cmap='gray')
plt.title('Imagen de prueba')
plt.show()

prediccion = model.predict(np.array([imagen]))
print("Predicción:", np.argmax(prediccion))
print("Etiqueta real:", y_test[0])

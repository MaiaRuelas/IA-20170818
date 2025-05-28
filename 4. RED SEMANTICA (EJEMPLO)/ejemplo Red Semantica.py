# Red semántica representada como diccionario
red_semantica = {
    "perro": {
        "es_un": "mamífero",
        "tiene": ["cuatro patas", "pelaje"],
        "puede": ["ladrar", "correr"]
    },
    "gato": {
        "es_un": "mamífero",
        "tiene": ["bigotes", "pelaje"],
        "puede": ["maullar", "saltar"]
    },
    "mamífero": {
        "es_un": "animal",
        "características": ["sangre caliente", "amamanta a sus crías"]
    },
    "animal": {
        "características": ["está vivo", "se mueve"]
    }
}

# Función para mostrar relaciones de un concepto
def mostrar_relaciones(concepto):
    if concepto in red_semantica:
        print(f"Relaciones de '{concepto}':")
        for relacion, valor in red_semantica[concepto].items():
            print(f"  - {relacion} ➝ {valor}")
    else:
        print(f"'{concepto}' no está en la red semántica.")

# Probar con algunos conceptos
mostrar_relaciones("perro")
print()
mostrar_relaciones("mamífero")

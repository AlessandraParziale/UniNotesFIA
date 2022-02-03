import pickle
import pandas as pd
import json
from json import JSONEncoder
import numpy as np
import flask
from flask import jsonify, request

# Flask permette di trattare script Python come Web Service
# In questo caso, mette a disposizione i servizi delle funzioni
# in questo file con un mapping all'indirizzo localhost:5000

#Istanziamo poi la nostra Web App Flask.
app = flask.Flask(__name__)
app.config["DEBUG"] = True


# Funzione per leggere il modello già allenato da un file
def readModelKMeans():
    read_file = open('agente.obj', 'rb')
    model = pickle.load(read_file)
    return model

def readModelDecisionTree():
    read_file = open('agenteDecisionTree.obj', 'rb')
    model = pickle.load(read_file)
    return model

class NumpyEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return json.JSONEncoder.default(self, obj)


def makePrediction(d1, d2, d3, d4, d5, d6, d7, d8):
    # Chiamo la funzione che legge il modello da file
    modelKMeans = readModelKMeans()
    modelDecisionTree = readModelDecisionTree()
    # effettuo la predizione, salvandola nella lista
    predizioneKMeans = modelKMeans.predict([[d1, d2, d3, d4, d5, d6, d7, d8]])
    predizioneDecisionThree = modelDecisionTree.predict([[d1, d2, d3, d4, d5, d6, d7, d8]])



    # Formatto la risposta in una lista, in quanto il metodo model.predict() restituisce
    # un formato diverso da quello che serve.
    results = []
    results.append(predizioneKMeans)
    results.append(predizioneDecisionThree)
    print(results)
    predizione = json.dumps(results, cls=NumpyEncoder)
    print(predizione[2]+","+predizione[7])
    return predizione[2]+","+predizione[7]


# Questo metodo si occupa di ricevere una chiamata HTTP, in particolare
# una POST, all'indirizzo localhost:5000/
# Riceve un JSON contenente le 7 risposte dell'utente, chiama il metodo prediction
# e restituisce la lista di domande restituiti sottoforma di JSON
@app.route('/', methods=['POST'])
def home():

    json = request.json
    return jsonify(makePrediction(json['d1'], json['d2'], json['d3'], json['d4'], json['d5'], json['d6'], json['d7'], json['d8']))


app.run()


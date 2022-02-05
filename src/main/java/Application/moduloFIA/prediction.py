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


# Funzione per leggere il modello KMeans già allenato da un file
def readModelKMeans():
    read_file = open('agenteKMeans.obj', 'rb')
    model = pickle.load(read_file)
    return model

# Funzione per leggere il modello DecisionTree già allenato da un file
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
    # Chiamo le funzioni che leggono i modelli dai due file
    modelKMeans = readModelKMeans()
    modelDecisionTree = readModelDecisionTree()
    # effettuo la predizione
    predizioneKMeans = modelKMeans.predict([[d1, d2, d3, d4, d5, d6, d7, d8]])
    predizioneDecisionThree = modelDecisionTree.predict([[d1, d2, d3, d4, d5, d6, d7, d8]])



   #json.dumps(results, cls=NumpyEncoder) serve per serializzare le predizioni
    results = []
    results.append(predizioneKMeans)
    results.append(predizioneDecisionThree)
    print(results)
    predizione = json.dumps(results, cls=NumpyEncoder)
    print(predizione[2]+","+predizione[7])
    return predizione[2]+","+predizione[7]


# Questo metodo si occupa di restare in ascolto su localhost:5000/ per ricevere delle richeste JSON
# e restituisce la predizione
@app.route('/', methods=['POST'])
def home():

    json = request.json
    return jsonify(makePrediction(json['d1'], json['d2'], json['d3'], json['d4'], json['d5'], json['d6'], json['d7'], json['d8']))


app.run()


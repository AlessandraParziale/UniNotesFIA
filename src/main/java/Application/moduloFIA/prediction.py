import pickle
import pandas as pd
import flask
from flask import jsonify, request

# Flask permette di trattare script Python come Web Service
# In questo caso, mette a disposizione i servizi delle funzioni
# in questo file con un mapping all'indirizzo localhost:5000

#Istanziamo poi la nostra Web App Flask.
app = flask.Flask(__name__)
app.config["DEBUG"] = True


# Funzione per leggere il modello già allenato da un file
def readModel():
    read_file = open('agente.obj', 'rb')
    model = pickle.load(read_file)
    return model


def makePrediction(d1, d2, d3, d4, d5):
    # Chiamo la funzione che legge il modello da file
    model = readModel()

    # effettuo la predizione, salvandola nella lista
    predizione = model.predict(pd.DataFrame({d1, d2, d3, d4, d5}))




    # Formatto la risposta in una lista, in quanto il metodo model.predict() restituisce
    # un formato diverso da quello che serve.
    results = []

    tmp = pd.DataFrame(predizione)
    results.append(tmp.loc[0, 'magistrale'])

    return results


# Questo metodo si occupa di ricevere una chiamata HTTP, in particolare
# una POST, all'indirizzo localhost:5000/
# Riceve un JSON contenente le 5 risposte dell'utente, chiama il metodo prediction
# e restituisce la lista di domande restituiti sottoforma di JSON
@app.route('/', methods=['POST'])
def home():

    json = request.json
    return jsonify(makePrediction(json['d1'], json['d2'], json['d3'], json['d4'], json['d5']))


app.run()


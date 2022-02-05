from __future__ import division, print_function, unicode_literals

import os
import pickle

import matplotlib.pyplot as plt
import pandas as pd
from imblearn.over_sampling import SMOTE
from sklearn.cluster import KMeans
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler


#Funzione per salvare e aggiornare il modello in un file
def saveModel(model):
    save_file = open("agenteKMeans.obj", 'wb')
    pickle.dump(model, save_file)


#Leggo i dati dal dataset artificiale
datapath = os.path.join("magistrale", "")
dataset = pd.read_csv(datapath + "DataSetArtificiale.csv")

#X sono le variabili indipendenti
X= dataset.iloc[ : , 2:10]

#Y variabile dipendete
y= dataset.iloc[ : , 1]


#dividere il dataset 20% per il test e 80% per il train


X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)


print("X_train dataset: ", X_train.shape)
print("y_train dataset: ", y_train.shape)
print("X_test dataset: ", X_test.shape)
print("y_test dataset: ", y_test.shape)




#La funzione smote porta le istanze minoritaria al numero delle istanze maggioritarie

print("Prima dell'OverSampling, il numero delle label '0': {}".format(sum(y_train == 0)))
print("Prima dell'OverSampling, il numero delle label '1': {}".format(sum(y_train == 1)))
print("Prima dell'OverSampling, il numero delle label '2': {}".format(sum(y_train == 2)))
print("Prima dell'OverSampling, il numero delle label '3': {}".format(sum(y_train == 3)))
print("Prima dell'OverSampling, il numero delle label '4': {}".format(sum(y_train == 4)))

sm = SMOTE(random_state=42)
X_train_res, y_train_res = sm.fit_resample(X_train, y_train)

print("Dopo l'OverSampling, il numero di label '0': {}".format(sum(y_train_res == 0)))
print("Dopo l'OverSampling, il numero di label '1': {}".format(sum(y_train_res == 1)))
print("Dopo l'OverSampling, il numero di label '2': {}".format(sum(y_train_res == 2)))
print("Dopo l'OverSampling, il numero di label '3': {}".format(sum(y_train_res == 3)))
print("Dopo l'OverSampling, il numero di label '4': {}".format(sum(y_train_res == 4)))


#Allenamento Kmeans
kmeans = KMeans(n_clusters=5, random_state=0).fit(X_train)
ris = kmeans.predict(X_test)
centroids = kmeans.cluster_centers_


saveModel(kmeans)
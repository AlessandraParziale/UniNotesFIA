from __future__ import division, print_function, unicode_literals
import pandas as pd
import matplotlib.pyplot as plt
import pickle
import os
from sklearn.cluster import KMeans
import numpy as np


from sklearn.model_selection import train_test_split
from imblearn.over_sampling import SMOTE
from sklearn.preprocessing import MinMaxScaler

from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import chi2, f_regression
from numpy import set_printoptions

#Funzione per salvare e aggiornare il modello in un file
def saveModel(model):
    save_file = open("agenteKMeans.obj", 'wb')
    pickle.dump(model, save_file)

def verBilanciamento(dataset):
    pd.value_counts(dataset['magistrale']).plot.bar()
    plt.xlabel('magistrale')
    plt.ylabel('Frequency')
    dataset['magistrale'].value_counts()


#Leggo i dati dal dataset artificiale
datapath = os.path.join("magistrale", "")
dataset = pd.read_csv(datapath + "DataSetReale.csv")

#X sono le variabili indipendenti
X= dataset.iloc[ : , 2:10]

#Y variabile dipendete
y= dataset.iloc[ : , 1]

#Richiamiamo verBilanciamento
verBilanciamento(dataset)


#dividere il dataset 20% per il test e 80% per il train


X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

print("Number transactions X_train dataset: ", X_train.shape)
print("Number transactions y_train dataset: ", y_train.shape)
print("Number transactions X_test dataset: ", X_test.shape)
print("Number transactions y_test dataset: ", y_test.shape)



#La funzione smote porta le istanze minoritaria al numero delle istanze maggioritarie

print("Before OverSampling, counts of label '0': {}".format(sum(y_train == 0)))
print("Before OverSampling, counts of label '1': {}".format(sum(y_train == 1)))
print("Before OverSampling, counts of label '2': {}".format(sum(y_train == 2)))
print("Before OverSampling, counts of label '3': {}".format(sum(y_train == 3)))
print("Before OverSampling, counts of label '4': {}".format(sum(y_train == 4)))

sm = SMOTE(k_neighbors=1)
X_train_res, y_train_res = sm.fit_resample(X_train, y_train)

print("After OverSampling, counts of label '0': {}".format(sum(y_train_res == 0)))
print("After OverSampling, counts of label '1': {}".format(sum(y_train_res == 1)))
print("After OverSampling, counts of label '2': {}".format(sum(y_train_res == 2)))
print("After OverSampling, counts of label '3': {}".format(sum(y_train_res == 3)))
print("After OverSampling, counts of label '4': {}".format(sum(y_train_res == 4)))

#Normalizzazione dei dati
scl = MinMaxScaler(feature_range=(0, 1))
X_train_res = scl.fit_transform(X_train_res)
x_test = scl.fit_transform(X_test)

print(X_train_res)

#feature selection supervised

#fs = SelectKBest(score_func=chi2,k=5)
#fs.fit_transform(X_train_res, y_train_res)

#X_new_train_res = fs.transform(X_train_res)
#X_new_test = fs.transform(X_test)
#print(X_new_train_res.shape)


#X.columns[fs.get_support(indices=True)]

#X.columns[fs.get_support(indices=True)].tolist()
#print(X.columns[fs.get_support(indices=True)].tolist())

#Allenamento Kmeans
kmeans = KMeans(n_clusters=5, random_state=0).fit(X_train_res)
ris = kmeans.predict(X_test)
centroids = kmeans.cluster_centers_
count = 0
for i in range(len(y_test)):
    if ris[i] == y_test.values[i]:
        count += 1

print(count)

saveModel(kmeans)
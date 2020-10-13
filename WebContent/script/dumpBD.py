#!/usr/bin/python
# -*- coding: utf-8 -*-

#--------------------------------------------------------------------
# Définition des paramètres
#--------------------------------------------------------------------
dump_path = "/opt/webapps/r2t/mysqldump"
db_host   = "localhost"
db_user   = "root"
db_pass   = "toulon"
db_name   = "r2t"

#--------------------------------------------------------------------
# les imports
#--------------------------------------------------------------------
import time
import sys
import os
import errno
import commands
import tarfile

#--------------------------------------------------------------------
# Variable du script
#--------------------------------------------------------------------
date_str   = time.strftime("%Y-%m-%d %H:%M:%S")
day_str    = time.strftime("%A")
out_str    = "[ %s ] (dump BD R2T)" % date_str

#--------------------------------------------------------------------
# Gestion des erreurs:
#--------------------------------------------------------------------
#le chemin existe t'il
if not os.path.exists(dump_path):
	print out_str, "ERREUR : '%s' n'existe pas" % dump_path
	sys.exit(errno.ENOENT)

#est ce un dossier valide
if not os.path.isdir(dump_path):
	print out_str, "ERREUR : '%s' n'est pas un dossier" % dump_path
	sys.exit(errno.ENOTDIR)

#--------------------------------------------------------------------
# Dump de la BD:
#--------------------------------------------------------------------
outfile_str = day_str + ".sql"
tarfile_str = day_str + ".tar.gz"
Mysql_conn = (db_user,db_pass,db_host,db_name,outfile_str)

#Dump
os.chdir(dump_path)
status,output = commands.getstatusoutput('mysqldump "-u%s" "-p%s" "-h%s" "%s" > %s' % Mysql_conn)
#reussite
if status:
	print out_str, "ERREUR: " + output
	sys.exit(status)

#compression
try:
	tar = tarfile.open(tarfile_str , "w:gz")
	tar.add(outfile_str)
	tar.close()
except:
	print out_str, "ERREUR : %s" % sys.exc_info()[1]
	sys.exit()
os.remove(outfile_str)

print out_str,"Réussie: %s créé" % tarfile_str

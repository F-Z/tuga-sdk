#!/bin/sh

export VERSAO=1.1
export DISTRO=/home/desenvolvimento/tuga-sdk/distro/TuGA
export TMP_DIR=/tmp/tuga-sdk/tuga
export PROJETO_DIR=/home/desenvolvimento/tuga-sdk/distro/TuGA/current

echo "Criando: TuGA.Runtime $VERSAO"
rm -fR $TMP_DIR
mkdir -p $TMP_DIR

echo "    Copiando Arquivos..."
cp -vR $PROJETO_DIR/* $TMP_DIR

cd $TMP_DIR; cd ..


echo "    Criando pacote... (Zip - All)"
zip -vrq9 $DISTRO/TuGA.Runtime.$VERSAO.bin.all.zip * -x *svn* *cvsignore* *xcf* *log*


rm -vfR $TMP_DIR
echo "    Pacote Finalizado"

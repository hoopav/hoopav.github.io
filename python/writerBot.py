# Work with Python 3.6
import pprint
import pymongo
from pymongo import MongoClient
import os
import discord
import re
import urllib.request
import lxml
from lxml import etree
from bs4 import BeautifulSoup

#setup mongo

mClient=MongoClient(os.environ['mongodbURI'])
db=mClient.writingexamples

#collection=db[dbname]

#pprint.pprint(collection.find_one())
#pprint.pprint(collection.find_one({"id":"value"}))

TOKEN = os.environ['token']
client = discord.Client()

def getRandomSentence(collection):
    pipeline=[ { "$sample": { "size": 1 } } ]#random sample
    jstring=collection.aggregate(pipeline)#returns a cursor
    result = list(jstring)#converts cursor to list
    result=result[0]#dict
    
    return result['sentence']

helpmsg='!action\n!dialogue\n!thought\n!emotion\n!summary\n!description'

@client.event
async def on_message(message):
    # we do not want the bot to reply to itself
    if message.author == client.user:
        return

    if message.content.startswith('!help'):
        msg = helpmsg.format(message)
        await client.send_message(message.channel, msg)

    if message.content.startswith('!hello'):
        msg = 'Hello {0.author.mention}'.format(message)
        await client.send_message(message.channel, msg)

    if message.content.startswith('!test'):
        msg = 'It appears that {0.author.mention} is acting up again.'.format(message)
        await client.send_message(message.channel, msg)

    if message.content.startswith('!action'):
        collection=db['action']
        
        msg = getRandomSentence(collection)
        await client.send_message(message.channel, msg)

    if message.content.startswith('!dialogue'):
        collection=db['dialogue']
        
        msg = getRandomSentence(collection)
        await client.send_message(message.channel, msg)

    if message.content.startswith('!thought'):
        collection=db['thoughts']
        
        msg = getRandomSentence(collection)
        await client.send_message(message.channel, msg)
            
    if message.content.startswith('!emotion'):
        collection=db['interior_emotion']
        
        msg = getRandomSentence(collection)
        await client.send_message(message.channel, msg)

    if message.content.startswith('!summary'):
        collection=db['narrative_summary']
        
        msg = getRandomSentence(collection)
        await client.send_message(message.channel, msg)

    if message.content.startswith('!description'):
        collection=db['description']
        
        msg = getRandomSentence(collection)
        await client.send_message(message.channel, msg)
        
@client.event
async def on_ready():
    print('Logged in as')
    print(client.user.name)
    print(client.user.id)
    print('------')

client.run(TOKEN)

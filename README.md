Exercice 2 :

Pourquoi est-il important de ne pas inclure la clé API directement dans le code source ?
	-> Pour des raisons de sécurité, exposer sa clé API dans le code source reviens à l'exposer au 	publique et donc de risquer une utilisation malveillante ou abusive qui peuvent résulter à un 	coût financier important.
 	-> Les solutions possible pour éviter ça sont :
		- Utiliser des variables d'environnement
		- utiliser des fichiers de configuration
 		- Utiliser des gestionnaires de secret (Vault ou AWS Secrets Manager)

Exercice 3 :
Quelle est la différence entre une base SQL et MongoDB pour stocker ce type de données ? 

	-> SQL : Utilise des tables avec des relations normalisées (via des clés étrangères). Convient 	aux données structurées avec des relations complexes nécessitant des jointures.

	-> MongoDB : Stocke les données sous forme de documents JSON imbriqués. Idéal pour des données 	flexibles, hiérarchiques, ou nécessitant des accès rapides sans jointures.


Dans quel cas choisiriez-vous MongoDB plutôt qu’une base relationnelle ?
MongoDB est préférable pour les projets où les données sont non structurée, qu'il y a une hiérarchie imbriquée, un besoin important de performance en lecture ou si vous avez besoin d'une scalabilité horizontale

Exercice 4 :

Combiner une API externe comme ChatGPT avec MongoDB permet de stocker et structurer des données générées ou utilisées par l'API, facilitant leur réutilisation, la persistance des interactions, et la personnalisation des réponses pour les utilisateurs. L'utilisation de l'api permet de pouvoir générer du contenu personnalisé au besoin soit de l'application soit de l'utilisateur directement. La base de donnée permet de stocker les données générer pas l'api (personnalisation, contenue etc...).

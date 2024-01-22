# Follow these steps

1. Download the newest java version

    Link: https://adoptium.net/de/

2. Save the tar-file anywhere you want

3. Go to the folder where you saved the tar

4. Open the terminal and type in the following command to unpack the tar

    ```bash
    tar zxvf OpenJDK21U-jdk_x64_linux_hotspot_21.0.1_12.tar.gz
    ```

5. Now you have a nice new folder with the jdk

6. Now adapt the bashrc file

    ```bash
    nano ~/.bashrc
    ```

    add the following lines to the end of the file
   
    ```bash 
    export PATH=$PATH:$JAVA_HOME/bin (not sure about this one. I think that is one of my others)
    export JAVA_HOME=/YourPathToTheJDK/jdk-21.0.1+12
    export PATH=$PATH:$JAVA_HOME/bin
    ```
   
   To apply the changes run
    
    ```bash
    source ~/.bashrc
    ```

7. Now you can check if everything worked by running

    ```bash
    java -version
    ```

    If you see the correct version you are good to go.

## btw, If the previous steps didn't work please follow these guides respectively

https://adoptium.net/de/

https://www.java.com/en/download/help/linux_install.html

https://www.webhi.com/how-to/how-to-install-java-with-apt-get-on-ubuntu-debian/

# everything from now on is just suggestions from co-pilot, but rather interesting. If you just wanna play TicTacToe tho, just stop here.

8. Now you can install the maven package

    ```bash
    sudo apt install maven
    ```

9. Now you can check if everything worked by running

    ```bash
    mvn -version
    ```

    If you see the correct version you are good to go.

10. Now you can install the git package

    ```bash
    sudo apt install git
    ```

11. Now you can check if everything worked by running

    ```bash
    git --version
    ```

    If you see the correct version you are good to go.

12. Now you can install the docker package

    ```bash

    sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release

    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

    echo \
    "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

    sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io

    sudo docker run hello-world
    ```

13. Now you can install the docker-compose package

    ```bash
    sudo apt install docker-compose
    ```

14. Now you can check if everything worked by running

    ```bash
    docker-compose --version
    ```

    If you see the correct version you are good to go.

15. Now you can install the node package

    ```bash
    sudo apt install nodejs
    ```

16. Now you can check if everything worked by running

    ```bash
    node -v
    ```

    If you see the correct version you are good to go.

17. Now you can install the npm package

    ```bash
    sudo apt install npm
    ```

18. Now you can check if everything worked by running

    ```bash
    npm -v
    ```

    If you see the correct version you are good to go.

19. Now you can install the angular package

    ```bash
    sudo npm install -g @angular/cli
    ```

20. Now you can check if everything worked by running

    ```bash
    ng --version
    ```

    If you see the correct version you are good to go.

21. Now you can install the python package

    ```bash
    sudo apt install python3
    ```

22. Now you can check if everything worked by running

    ```bash
    python3 --version
    ```

    If you see the correct version you are good to go.

23. Now you can install the pip package

    ```bash
    sudo apt install python3-pip
    ```

24. Now you can check if everything worked by running

    ```bash
    pip3 --version
    ```

    If you see the correct version you are good to go.

25. Now you can install the awscli package

    ```bash
    pip3 install awscli --upgrade --user
    ```

26. Now you can check if everything worked by running

    ```bash
    aws --version
    ```

    If you see the correct version you are good to go.

27. Now you can install the terraform package

    ```bash
    sudo apt install unzip
    wget https://releases.hashicorp.com/terraform/1.0.8/terraform_1.0.8_linux_amd64.zip
    unzip terraform_1.0.8_linux_amd64.zip
    sudo mv terraform /usr/local/bin/
    ```

28. Now you can check if everything worked by running

    ```bash
    terraform --version
    ```

    If you see the correct version you are good to go.

29. Now you can install the kubectl package

    ```bash
    sudo apt-get update && sudo apt-get install -y apt-transport-https gnupg2 curl
    curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
    echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list
    sudo apt-get update
    sudo apt-get install -y kubectl
    ```

30. Now you can check if everything worked by running

    ```bash
    kubectl version --client
    ```

    If you see the correct version you are good to go.

31. Now you can install the helm package

    ```bash
    curl https://baltocdn.com/helm/signing.asc | sudo apt-key add -
    sudo apt-get install apt-transport-https --yes
    echo "deb https://baltocdn.com/helm/stable/debian/ all main" | sudo tee /etc/apt/sources.list.d/helm-stable-debian.list
    sudo apt-get update
    sudo apt-get install helm
    ```

32. Now you can check if everything worked by running

    ```bash
    helm version
    ```

    If you see the correct version you are good to go.

33. Now you can install the aws-iam-authenticator package

    ```bash
    curl -o aws-iam-authenticator https://amazon-eks.s3.us-west-2.amazonaws.com/1.21.2/2021-07-05/bin/linux/amd64/aws-iam-authenticator
    chmod +x ./aws-iam-authenticator
    mkdir -p $HOME/bin && cp ./aws-iam-authenticator $HOME/bin/aws-iam-authenticator && export PATH=$PATH:$HOME/bin
    echo 'export PATH=$PATH:$HOME/bin' >> ~/.bashrc
    ```

34. Now you can check if everything worked by running

    ```bash
    aws-iam-authenticator help
    ```

    If you see the correct version you are good to go.

    ## yeah I'm gonna stop it now. I#m tired and I think you get the point. Just install the packages you need and you are good to go.

    ## btw, if you wanna install the aws cli v2, you can do it like this

    ```bash
    curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
    unzip awscliv2.zip
    sudo ./aws/install
    ```

    ## and if you wanna install the aws cli v1, you can do it like this

    ```bash
    curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv1.zip"
    unzip awscliv1.zip
    sudo ./aws/install
    ```

    ## and if you wanna install the aws cli v1, you can do it like this

    ```bash
    curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv1.zip"
    unzip awscliv1.zip
    sudo ./aws/install
    ```

    ## CHILL THE FUCK OUT COPILOT
kubectl create -f nginx-deploy-hpa.yaml
kubectl autoscale deployment nginx-deploy-hpa --cpu-percent=30 --min=2 --max=10
kubectl get hpa nginx-deploy-hpa -o yaml
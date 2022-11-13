import logging
from jaeger_client import Config
import requests

def init_tracer(service):
    logging.getLogger('').handlers = []
    logging.basicConfig(format='%(message)s', level=logging.DEBUG)

    config = Config(
        config={
            'sampler': {
                'type': 'const',
                'param': 1,
            },
            'logging': True,
        },
        service_name=service,
    )

    # this call also sets opentracing.tracer
    return config.initialize_tracer()

tracer = init_tracer('first-service')

with tracer.start_span('get-ip-api-jobs') as span:
    try:
        res = requests.get('http://ip-api.com/json/naver.com')
        result = res.json()
        print('Getting status %s' % result['status'])
        span.set_tag('jobs-count', len(res.json()))
        for k in result.keys():
            span.set_tag(k, result[k])

    except:
        print('Unable to get site for')

input('')
